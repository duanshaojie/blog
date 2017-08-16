package cn.duanshaojie.utils;

import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

public class ResponseBuildHelper {
	public static Response buildSuccessResponse(Response.Status status, Object entity){
        Response response = Response.status(status).entity(entity).build();
        return response;
	}
	public static Response BuildErrorResponse(Response.Status status, String errorMessage){
		JSONObject result = new JSONObject();
        result.put("message",errorMessage);
        Response response = Response.status(status).entity(result).build();
        return response;
	}
}
