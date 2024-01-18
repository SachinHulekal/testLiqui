package testLiqui;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

// Handler value: example.HandlerInteger
public class testClass implements RequestHandler<Integer, String>{

  @Override
  /*
   * Takes in an InputRecord, which contains two integers and a String.
   * Logs the String, then returns the sum of the two Integers.
   */
  public String handleRequest(Integer event, Context context)
  {
    return String.valueOf(event);
  }
}
