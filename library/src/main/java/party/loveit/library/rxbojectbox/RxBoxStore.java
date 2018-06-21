package party.loveit.library.rxbojectbox;

import java.util.Collection;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.Property;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * RxBoxStore rxjava2 implements
 * *
 * Copyright (C) 2018
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
public abstract class RxBoxStore {
    /**
     *
     * @param boxStore
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Box<T> getBox(BoxStore boxStore, Class<T> tClass) {
        return boxStore.boxFor(tClass);
    }

    /**
     * @param boxStore
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Single<Long> put(final BoxStore boxStore, final T t) {

        return Single.create(new SingleOnSubscribe<Long>() {
            @Override
            public void subscribe(SingleEmitter<Long> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = (Box<T>) getBox(boxStore, t.getClass());
                    e.onSuccess(box.put(t));
                }
            }
        });
    }

    /**
     * @param box
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> put(final Box<T> box, final T... t) {

        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.put(t);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> put(final Box<T> box, final Collection<T> list) {

        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.put(list);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> find(final Box<T> box, final Property property, final String value) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.find(property, value));
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param tClass
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> find(final BoxStore boxStore, final Class<T> tClass, final Property property, final String value) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = getBox(boxStore, tClass);
                    e.onSuccess(box.find(property, value));
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param tClass
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> find(final BoxStore boxStore, final Class<T> tClass, final Property property, final long value) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = getBox(boxStore, tClass);
                    e.onSuccess(box.find(property, value));
                }
            }
        });
    }

    /**
     * @param box
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> find(final Box<T> box, final Property property, final long value) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.find(property, value));
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param tClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> Single<T> get(final BoxStore boxStore, final Class<T> tClass, final long id) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = getBox(boxStore, tClass);
                    e.onSuccess(box.get(id));
                }
            }
        });
    }

    /**
     * @param box
     * @param id
     * @param <T>
     * @return
     */
    public static <T> Single<T> get(final Box<T> box, final long id) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.get(id));
                }
            }
        });
    }

    /**
     * @param box
     * @param ids
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> get(final Box<T> box, final long[] ids) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.get(ids));
                }
            }
        });
    }

    /**
     * @param box
     * @param ids
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> get(final Box<T> box, final Iterable<Long> ids) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.get(ids));
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Single<Long> count(final BoxStore boxStore, final Class<T> tClass) {

        return Single.create(new SingleOnSubscribe<Long>() {
            @Override
            public void subscribe(SingleEmitter<Long> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = getBox(boxStore, tClass);
                    e.onSuccess(box.count());
                }
            }
        });
    }

    /**
     * @param box
     * @param <T>
     * @return
     */
    public static <T> Single<Long> count(final Box<T> box) {

        return Single.create(new SingleOnSubscribe<Long>() {
            @Override
            public void subscribe(SingleEmitter<Long> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.count());
                }
            }
        });
    }

    /**
     * @param box
     * @param max
     * @param <T>
     * @return
     */
    public static <T> Single<Long> count(final Box<T> box, final long max) {

        return Single.create(new SingleOnSubscribe<Long>() {
            @Override
            public void subscribe(SingleEmitter<Long> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.count(max));
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> getAll(final BoxStore boxStore, final Class<T> tClass) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = getBox(boxStore, tClass);
                    e.onSuccess(box.getAll());
                }
            }
        });
    }

    /**
     * @param box
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> getAll(final Box<T> box) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess(box.getAll());
                }
            }
        });
    }

    /**
     * @param box
     * @param id
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> remove(final Box<T> box, final long id) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.remove(id);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param id
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> remove(final Box<T> box, final long... id) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.remove(id);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> remove(final Box<T> box, final T t) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.remove(t);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> remove(final BoxStore boxStore, final T t) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = (Box<T>) getBox(boxStore, t.getClass());
                    box.remove(t);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> remove(final Box<T> box, final T... t) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.remove(t);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> remove(final Box<T> box, final Collection<T> t) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.remove(t);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param ids
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> removeByKeys(final Box<T> box, final Collection<Long> ids) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.removeByKeys(ids);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param tClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> remove(final BoxStore boxStore, final Class<T> tClass, final long id) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = getBox(boxStore, tClass);
                    box.remove(id);
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param box
     * @param <T>
     * @return Single<Boolean>
     */
    public static <T> Single<Boolean> removeAll(final Box<T> box) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    box.removeAll();
                    e.onSuccess(true);
                }
            }
        });
    }

    /**
     * @param boxStore
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Single<Boolean> removeAll(final BoxStore boxStore, final Class<T> tClass) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                if (!e.isDisposed()) {
                    Box<T> box = getBox(boxStore, tClass);
                    box.removeAll();
                    e.onSuccess(true);
                }
            }
        });
    }
}
