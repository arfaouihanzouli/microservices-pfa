package com.pfa.microserviceoffers.exceptions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder  implements ErrorDecoder {

    private final ErrorDecoder errorDecoder=new Default();

    @Override
    public Exception decode(String invoqueur, Response response) {
        if(response.status()==404)
        {
            Gson gson= new Gson();
            JsonObject jsonObject=gson.fromJson(response.body().toString(),JsonObject.class);
            return new UserBadRequestException(jsonObject.get("message").toString().substring(1,jsonObject.get("message").toString().length()-1));
        }
        return errorDecoder.decode(invoqueur,response);
    }
}
