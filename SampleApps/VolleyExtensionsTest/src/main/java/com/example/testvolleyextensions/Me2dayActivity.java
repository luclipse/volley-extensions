/*
 * Copyright (C) 2014 Naver Business Platform Corp.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.testvolleyextensions;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.nhncorp.volleyextensions.request.Jackson2Request;
import com.nhncorp.volleyextensions.request.JacksonRequest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;

public class Me2dayActivity extends Activity {

	private static final String GET_PERSON_URL = "http://me2day.net/api/get_person/codian.json";
	private RequestQueue requestQueue;
	private TextView personInfoTextForJackson2;
	private TextView personInfoTextForJackson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestQueue = MyVolley.getRequestQueue();
		setContentView(R.layout.activity_me2day);
		personInfoTextForJackson = (TextView) findViewById(R.id.personInfoTextForJackson);
		personInfoTextForJackson2 = (TextView) findViewById(R.id.personInfoTextForJackson2);
		loadPersonInfo();
	}

	private void loadPersonInfo() {
		JacksonRequest<Person> jacksonRequest = new JacksonRequest<Person>(
				GET_PERSON_URL, Person.class, new Listener<Person>() {

					@Override
					public void onResponse(Person person) {
						personInfoTextForJackson.setText(person.toString());
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Log.d(Me2dayActivity.class.getSimpleName(), ""
								+ volleyError.getMessage());
					}
				});

		Jackson2Request<Person> jackson2Request = new Jackson2Request<Person>(
				GET_PERSON_URL, Person.class, new Listener<Person>() {

					@Override
					public void onResponse(Person person) {
						personInfoTextForJackson2.setText(person.toString());
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Log.d(Me2dayActivity.class.getSimpleName(), ""
								+ volleyError.getMessage());
					}
				});
		requestQueue.add(jacksonRequest);
		requestQueue.add(jackson2Request);
	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

}
