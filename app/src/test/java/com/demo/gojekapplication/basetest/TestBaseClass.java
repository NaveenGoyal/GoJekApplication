package com.demo.gojekapplication.basetest;

import com.demo.gojekapplication.DemoApplication;
import com.demo.gojekapplication.data.webservices.WebServices;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@RunWith(JUnit4.class)
@Config(application = DemoApplication.class)
public abstract class TestBaseClass {

    private MockWebServer mockWebServer;
    public WebServices webServices;

    @BeforeClass
    public static void setUp(){

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.computation());
    }

    @AfterClass
    public static void close(){
        RxAndroidPlugins.reset();
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockServerSetUp();
    }

    private void mockServerSetUp() {
        if(hasMockServer()) {
            mockWebServer =  new MockWebServer();
            try {
                 mockWebServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(mockWebServer.url("/").toString())
                    .client(new OkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
            webServices = builder.build().create(WebServices.class);
        }
    }

    public abstract boolean hasMockServer();

    public void mockHttpResponse(String file, int responseCode) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJsonFromFile(file)));
    }

    private String getJsonFromFile(String file) {
        URL uri = this.getClass().getClassLoader().getResource(file);
        try {
            return getStringFromFile(uri.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getStringFromFile (String filePath) throws IOException {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = streamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    private String streamToString(FileInputStream fin) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    @After
    public void stopServer() {
        if (hasMockServer()){
            try {
                if(mockWebServer != null)
                    mockWebServer.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
