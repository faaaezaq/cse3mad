package com.example.mobileappdev;

public class Users{
        private String Name;
        private String Email;
        private String Password;

        public Users(String name, String email, String password) {
                Name = name;
                Email = email;
                Password = password;
        }

        public Users() { }

        public String getName(){
                return Name;
        }

        public void setName(String name) {
                Name = name;
        }

        public String getEmail(){
                return Email;
        }

        public void setEmail(String email){
                Email = email;
        }

        public String getPassword(){
                return Password;
        }

        public void setPassword(String password){
                Password = password;
        }

        @Override
        public String toString(){
                return "Users{" + "Name = " + Name + '\'' + ", Email = " + Email + '\'' + ", Password = " + Password + '\'' + "}";
        }
}
