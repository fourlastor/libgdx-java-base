package io.github.fourlastor.pool;

import com.badlogic.gdx.utils.Pool;

public class AutoPoolable<T> implements AutoCloseable, Pool.Poolable {

    private final Pool<AutoPoolable<T>> pool;
    private final Pool<T> tPool;
    private T t;

    public AutoPoolable(Pool<AutoPoolable<T>> pool, Pool<T> tPool) {
        this.pool = pool;
        this.tPool = tPool;
    }

    @Override
    public void close() {
        pool.free(this);
    }

    public T get() {
        return t;
    }

    void set(T t) {
        this.t = t;
    }

    @Override
    public void reset() {
        tPool.free(t);
        t = null;
    }
}
