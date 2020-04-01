package com.pfa.microserviceoffers.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder  implements ErrorDecoder {

    private final ErrorDecoder errorDecoder=new Default();

    @Override
    public Exception decode(String invoqueur, Response response) {
        if(response.status()==404)
        {
            System.out.println(invoqueur);
            System.out.println(response.body());
            System.out.println(response.toString());
            return new UserBadRequestException("Ce candidat n'existe pas");
        }
        return errorDecoder.decode(invoqueur,response);
    }
}
