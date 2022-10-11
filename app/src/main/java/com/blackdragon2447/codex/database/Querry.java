package com.blackdragon2447.codex.database;

import java.util.ArrayList;
import java.util.stream.Collectors;

public interface Querry {
  
  public static class Select implements Querry {

    private ArrayList<QuerryToken> tokens = new ArrayList<>();

    public Select(String selector, String table) {
      this.tokens.add(new QuerryToken.Select(selector));
      this.tokens.add(new QuerryToken.From(table));
    }

    public Select whereEqual(String column, String value) {
      this.tokens.add(QuerryToken.Where.equals(column, value));
      return this;
    }

    public String assemble() {

      return tokens.stream()
          .map((token) -> token.assemble())
          .collect(Collectors.joining(" "));
    }
  }

  abstract String assemble();

  interface QuerryToken {

    public class Select implements QuerryToken {

      private final String selector;

      protected Select(String selector) { this.selector = selector; }

      public String assemble() { return "SELECT " + selector; }
    }

    public class From implements QuerryToken {

      private final String table;

      protected From(String table) { this.table = table; }

      public String assemble() { return "FROM " + table; }
    }

    public class Where implements QuerryToken {

      private final Type type;
      private final String column;
      private final String value;

      public static Where equals(String column, String value){
        return new Where(Type.EQ, column, value);
      }

      private Where(Type type, String column, String value){
        this.type = type;
        this.column = column;
        this.value = value;
      }

      public String assemble() { return ""; }

      public enum Type {
        EQ,
        NE,
        LT,
        LE,
        GT,
        GE,
        IN,
        LIKE,
        BETWEEN;
      }

    }

    abstract String assemble();
  }
}
