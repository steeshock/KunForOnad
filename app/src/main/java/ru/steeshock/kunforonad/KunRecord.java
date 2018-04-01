package ru.steeshock.kunforonad;

import java.io.Serializable;

/**
 * Created by steeshock on 18.03.2018.
 */

public class KunRecord  implements Serializable{

     String date;
     String stage;
     String state;
     String series;

     @Override
     public String toString() {
          return "KunRecord{" +
                  "date='" + date + '\'' +
                  ", stage='" + stage + '\'' +
                  ", state='" + state + '\'' +
                  ", series='" + series + '\'' +
                  '}';
     }
}
