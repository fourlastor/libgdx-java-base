package io.github.fourlastor.pool;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class AutoPool<T> extends Pool<AutoPoolable<T>> {

    private final Pool<T> pool;

    public AutoPool(Pool<T> pool) {
        this.pool = pool;
    }

    public AutoPool(Class<T> type) {
        this(Pools.get(type, 15));
    }

    @Override
    public AutoPoolable<T> obtain() {
        AutoPoolable<T> instance = super.obtain();
        instance.set(pool.obtain());
        return instance;
    }

    @Override
    protected AutoPoolable<T> newObject() {
        return new AutoPoolable<>(this, pool);
    }
}
