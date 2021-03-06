package party.loveit.library.rxbojectbox;

/*
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
 */


import java.util.List;

import io.objectbox.query.Query;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Static methods to Rx-ify ObjectBox queries.
 */
public abstract class RxQuery {
    /**
     * The returned Flowable emits Query results one by one. Once all results have been processed, onComplete is called.
     * Uses BackpressureStrategy.BUFFER.
     */
    /*public static <T> Flowable<T> flowableOneByOne(final Query<T> query) {
        return flowableOneByOne(query, BackpressureStrategy.BUFFER);
    }

    *//**
     * The returned Flowable emits Query results one by one. Once all results have been processed, onComplete is called.
     * Uses given BackpressureStrategy.
     *//*
    public static <T> Flowable<T> flowableOneByOne(final Query<T> query, BackpressureStrategy strategy) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(final FlowableEmitter<T> emitter) throws Exception {
                createListItemEmitter(query, emitter);
            }

        }, strategy);
    }

    static <T> void createListItemEmitter(final Query<T> query, final FlowableEmitter<T> emitter) {
        final DataSubscription dataSubscription = query.subscribe().observer(new DataObserver<List<T>>() {
            @Override
            public void onData(List<T> data) {
                for (T datum : data) {
                    if (emitter.isCancelled()) {
                        return;
                    } else {
                        emitter.onNext(datum);
                    }
                }
                if (!emitter.isCancelled()) {
                    emitter.onComplete();
                }
            }
        });
        emitter.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                if (dataSubscription != null)
                    dataSubscription.cancel();
            }
        });
    }

    *//**
     * The returned Observable emits Query results as Lists.
     * Never completes, so you will get updates when underlying data changes.
     *//*
    public static <T> Observable<List<T>> observable(final Query<T> query) {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<T>> emitter) throws Exception {
                final DataSubscription dataSubscription = query.subscribe().observer(new DataObserver<List<T>>() {
                    @Override
                    public void onData(List<T> data) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(data);
                        }
                    }
                });
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        if (dataSubscription != null)
                            dataSubscription.cancel();
                    }
                });
            }
        });
    }

    */

    /**
     * The returned Single emits one Query result as a List.
     *//*
    public static <T> Single<List<T>> single(final Query<T> query) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(final SingleEmitter<List<T>> emitter) throws Exception {
                final DataSubscription dataSubscription = query.subscribe().single().observer(new DataObserver<List<T>>() {
                    @Override
                    public void onData(List<T> data) {
                        if (!emitter.isDisposed()) {
                            emitter.onSuccess(data);
                        }
                    }
                });
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        if (dataSubscription != null)
                            dataSubscription.cancel();
                    }
                });
            }
        });
    }*/

    /**
     * @param query
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> find(final Query<T> query) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    List<T> data = query.find();
                    if (data != null)
                        emitter.onSuccess(data);
                    else
                        emitter.onError(new NoDataException(" the data is null"));
                }
            }
        });
    }

    /**
     * 分页查询
     * @param query
     * @param offset
     * @param limit
     * @param <T>
     * @return
     */
    public static <T> Single<List<T>> find(final Query<T> query, final long offset, final long limit) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            @Override
            public void subscribe(SingleEmitter<List<T>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    List<T> data = query.find(offset, limit);
                    if (data != null)
                        emitter.onSuccess(data);
                    else
                        emitter.onError(new NoDataException(" the data is null"));
                }
            }
        });
    }

    /**
     *
     * @param query
     * @param <T>
     * @return
     */
    public static <T> Single<T> findFirst(final Query<T> query) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    T data = query.findFirst();
                    if (data != null)
                        emitter.onSuccess(data);
                    else
                        emitter.onError(new NoDataException(" the data is null"));
                }
            }
        });
    }

    /**
     *
     * @param query
     * @param <T>
     * @return
     */
    public static <T> Single<T> findUnique(final Query<T> query) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    T data = query.findUnique();
                    if (data != null)
                        emitter.onSuccess(data);
                    else
                        emitter.onError(new NoDataException(" the data is null"));
                }
            }
        });
    }

}