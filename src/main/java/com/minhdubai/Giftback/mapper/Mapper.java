package com.minhdubai.Giftback.mapper;

public interface Mapper<A, B> {
   B mapTo(A a);

   A mapFrom(B b);
}
