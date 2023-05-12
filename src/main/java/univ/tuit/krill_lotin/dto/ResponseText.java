package univ.tuit.krill_lotin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResponseText {

   private String message;
   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }


}
