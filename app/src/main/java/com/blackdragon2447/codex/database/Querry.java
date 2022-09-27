package com.blackdragon2447.codex.database;

import java.util.ArrayList;
import java.util.stream.Collectors;

public interface Querry {
  
  public static class Select implements Querry {
   
    private ArrayList<QuerryToken> tokens = new ArrayList<>();

    public Select(String selector, String table){
      this.tokens.add(new QuerryToken.Select(selector)); 
      this.tokens.add(new QuerryToken.From(table));
    }

    public String assemble() {
      
      return tokens.stream().map((token) -> token.assemble()).collect(Collectors.joining(" "));

    }

  }

  abstract String assemble();

  interface QuerryToken {

    public class Select implements QuerryToken {
      
      private final String selector;

      protected Select(String selector){
        this.selector = selector;
      }

      public String assemble() {
        return "SELECT " + selector;
      }

    }

    public class From implements QuerryToken {

      private final String table;

      protected From(String table) {
        this.table = table;
      }

      public String assemble() {
        return "FROM " + table;
      }

    }

    public class Where implements QuerryToken {


      public String assemble() {
        

        return "";
      }

    }

    abstract String assemble();

  }

}
