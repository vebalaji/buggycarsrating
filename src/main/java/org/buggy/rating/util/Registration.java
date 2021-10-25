package org.buggy.rating.util;

public class Registration {
    public String username;
    public String firstname;
    public String lastname;
    public String password;
    public String confirmpassword;

    public static class Builder{
        private String username;
        private String firstname;
        private String lastname;
        private String password;
        private String confirmpassword;

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder firstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder confirmpassword(String password){
            this.confirmpassword = password;
            return this;
        }

        public Registration build() {
            return new Registration(this);
        }
    }

    private Registration(Builder builder){
        username = builder.username;
        firstname = builder.firstname;
        lastname = builder.lastname;
        password = builder.password;
        confirmpassword = builder.confirmpassword;
    }


}
