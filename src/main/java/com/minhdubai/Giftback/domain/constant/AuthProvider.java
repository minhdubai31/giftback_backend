package com.minhdubai.Giftback.domain.constant;

public enum AuthProvider {
   LOCAL("local", "local_"), 
   GOOGLE("google", "google_");

   private final String name;
   private final String prefix;

   AuthProvider(String name, String prefix) {
       this.name = name;
       this.prefix = prefix;
   }

   public String getName() {
       return name;
   }

   public String getPrefix() {
       return prefix;
   }
}
