package nl.capaxit.viewpercapability.service;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by jamiecraane on 31/05/2017.
 */

public class MockServer {
    public static MockWebServer mockWebServer;
    public static final BehaviorSubject<MockResponse> mockResponse = BehaviorSubject.create();

    static {
//        start mockwebserver in the background and use mockResponse behavior to push responses to the webserver w
        mockWebServer = new MockWebServer();
        mockResponse.subscribe(response -> mockWebServer.enqueue(response));
        Observable.fromCallable(() -> {
            mockWebServer.start();
            return Void.class;
        }).subscribeOn(Schedulers.io()).subscribe();
    }

}
