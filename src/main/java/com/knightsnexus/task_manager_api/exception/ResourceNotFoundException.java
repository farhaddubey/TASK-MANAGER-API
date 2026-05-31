package com.knightsnexus.task_manager_api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    /// We are initialzing construcction inside the class
    // We are invoking the super(); 
}
