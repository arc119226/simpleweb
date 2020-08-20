package com.hoyetec.api.error;

public class CustomErrorException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomErrorException(){
    };

    public CustomErrorException(CustomError error) {
        super();
        this.error = error;
    }

    private CustomError error;

    public CustomError getError() {
        return error;
    }

}
