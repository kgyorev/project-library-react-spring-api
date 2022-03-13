package com.example.demo.enums;

public enum Status {
    PENDING("Pending"),
    FINISHED("Finished");
    private final String text;

    /**
     * @param text
     */
    Status(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String getStatusLabel() {
        return this.text;
    }
}
