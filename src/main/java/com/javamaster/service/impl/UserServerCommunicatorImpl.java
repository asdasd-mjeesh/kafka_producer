package com.javamaster.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javamaster.model.User;
import com.javamaster.service.UserServerCommunicator;
import jakarta.annotation.PostConstruct;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServerCommunicatorImpl implements UserServerCommunicator {
    private OkHttpClient okHttpClient;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Value("${http-communication.server-response-time-await}")
    private Long responseTimeAwait;
    @Value("${http-communication.users-api-url}")
    private String usersApiUrl;

    @PostConstruct
    private void setup() {
        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(responseTimeAwait, TimeUnit.SECONDS)
                .readTimeout(responseTimeAwait, TimeUnit.SECONDS)
                .writeTimeout(responseTimeAwait, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Optional<User> createUser(User user) {
        try {
            var userJsonString = OBJECT_MAPPER.writeValueAsString(user);
            var body = RequestBody.create(JSON, userJsonString);
            var request = new Request.Builder()
                    .url(usersApiUrl)
                    .post(body)
                    .build();
            var call = okHttpClient.newCall(request);
            var response = call.execute();

            if (response.code() == HttpStatus.CREATED.value()) {
                user = OBJECT_MAPPER.readValue(Objects.requireNonNull(response.body()).string(), User.class);
                return Optional.ofNullable(user);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try {
            var request = new Request.Builder()
                    .url(usersApiUrl + id)
                    .get()
                    .build();
            var call = okHttpClient.newCall(request);
            var response = call.execute();

            if (response.code() == HttpStatus.OK.value()) {
                User user = OBJECT_MAPPER.readValue(Objects.requireNonNull(response.body()).string(), User.class);
                return Optional.ofNullable(user);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            var request = new Request.Builder()
                    .url(usersApiUrl)
                    .get()
                    .build();
            var call = okHttpClient.newCall(request);
            var response = call.execute();

            if (response.code() == HttpStatus.OK.value()) {
                return OBJECT_MAPPER.readValue(Objects.requireNonNull(response.body()).string(), List.class);
            }
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUserById(Long id) {
        try {
            var request = new Request.Builder()
                    .url(usersApiUrl + id)
                    .delete()
                    .build();
            var call = okHttpClient.newCall(request);
            var response = call.execute();

            return response.code() == HttpStatus.OK.value();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
