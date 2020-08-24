package com.aj.client.jwtpt;


interface ICallJwtService {


	byte[] getObject(String method,in byte[] param);

	byte[] getCurrentUser(in byte[] param);


}
