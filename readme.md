# CacheSample

## やりたいこと

- キャッシュ/APIの自動切り替え・取得
- キャッシュへの保存
- キャッシュの監視
- 同一ソースによる異なるキャッシュの同期

### キャッシュ/APIの自動切り替え・取得

キャッシュが無効な値を返したときに、APIの値を取得するようにする。
キャッシュIFは任意の型の有効な値と無効な値を返す。無効な値はnullとする。

```
interface Cache<T> {
  fun get(): T?
}
```

APIは有効な任意の型の値を返す。

```
interface Api<T> {
  fun get(): T
}
```

キャッシュがnullを返したとき、APIの値を返すようにする。

```
val api: Api<T>
val cache: Cache<T>

fun get(): T {
  return cache.get() ?: api.get()
}
```

### キャッシュへの保存

キャッシュIFは任意の型の有効な値を保存のために受けることができるようにする。

```
interface Cache<T> {
  fun get(): T?
  fun set(value: T)
}
```

キャッシュへはAPIの値を保存する。

```
val api: Api<T>
val cache: Cache<T>

fun get(): T {
  return cache.get() ?: api.get().also {
    cache.set(it)
  }
}
```

### キャッシュの監視

単純に値の監視だけであれば、KotlinのFlowを用いることで実現できる。
まずは任意の型のFlowを取得できれば良い。そのためのinterfaceを用意する。

```
interface FlowAccessor<T> {
  fun getFlow(): Flow<T>
}
```

キャッシュの監視のために、Cache、FlowAccessorの両方を実装したクラスを作成する。

```
class ChannelCache: Cache<T>, FlowAccessor<T> {
  private val channel = ConflatedBloadcastChannel<T>()

  fun get(): T? {
    return if (有効性確認) channel.value else null
  }
  fun set(value: T) {
    return channel.send(value)
  }
  fun getFlow(): Flow<T> {
    return channel.asFlow()
  }
}
```

### 同一ソースによる異なるキャッシュの同期

サーバー上では同一ソースだが、クライアント側で受信した時点でキャッシュが分かれてしまうことがある。
(例：詳細画面では完全なデータを表示し、一覧画面では不完全なデータを表示する)
その場合に、どちらかに変更があれば、それを別のキャッシュにも同期させたい。
クライアント側の都合で分かれているが、実際には同一のキャッシュだということを示す。

```
class MergeCache(
  private val mainCache: Cache<T>,
  private val subCaches: List<Cache<T>>
): Cache<T> {
  override fun get(): T? = mainCache.get()
  override fun set(value: T) {
    mainCache.set(value)
    subCaches.forEach { it.set(value) }
  }
}

val mergeCache = MergeCache(detailCache, listOf(listCache))
```

APIからの取得時に、mergeCacheが参照されるようになっていれば、すべてのキャッシュが更新される。

```
val api: Api<T>
val cache: Cache<T> = mergeCache

fun get(): T {
  return cache.get() ?: api.get().also {
    cache.set(it)
  }
}
```

