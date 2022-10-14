package com.blackdragon2447.codex.database;

import com.blackdragon2447.codex.database.Query.QueryToken.Where;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public interface Query {

  public static class Select implements Query {

    private ArrayList<QueryToken> tokens = new ArrayList<>();

    public Select(String selector, String table) {
      this.tokens.add(new QueryToken.Select(selector));
      this.tokens.add(new QueryToken.From(table));
    }

    public Select whereEqual(String column, String value) {
      this.tokens.add(QueryToken.Where.equals(column, value));
      return this;
    }

    public Select whereNotEqual(String column, String value) {
      this.tokens.add(QueryToken.Where.notEquals(column, value));
      return this;
    }

    public Select whereLessThan(String column, String value) {
      this.tokens.add(QueryToken.Where.lessThan(column, value));
      return this;
    }

    public Select whereLessThanOrEqual(String column, String value) {
      this.tokens.add(QueryToken.Where.lessOrEq(column, value));
      return this;
    }

    public Select whereGreaterThan(String column, String value) {
      this.tokens.add(QueryToken.Where.greaterThan(column, value));
      return this;
    }

    public Select whereGreaterThanOrEqual(String column, String value) {
      this.tokens.add(QueryToken.Where.greaterOrEq(column, value));
      return this;
    }

    public Select whereValueIn(String column, String... values) {
      this.tokens.add(QueryToken.Where.in(column, values));
      return this;
    }

    public Select whereValueLike(String column, String pattern) {
      this.tokens.add(QueryToken.Where.like(column, pattern));
      return this;
    }

    public Select whereValueBetween(String column, String min, String max) {
      this.tokens.add(QueryToken.Where.between(column, min, max));
      return this;
    }

    public String assemble() {

      final boolean[] hasWhere = {false};

      return tokens.stream()
          .map((token) -> {
            if (token instanceof Where) {
              if (hasWhere[0]) {
                return token.assemble().replace("WHERE", "AND");
              } else {
                hasWhere[0] = true;
              }
            }

            return token.assemble();
          })
          .collect(Collectors.joining(" ")) + ";";
    }
  }

  abstract String assemble();

  interface QueryToken {

    public class Select implements QueryToken {

      private final String selector;

      protected Select(String selector) { this.selector = selector; }

      public String assemble() { return "SELECT " + selector; }
    }

    public class From implements QueryToken {

      private final String table;

      protected From(String table) { this.table = table; }

      public String assemble() { return "FROM " + table; }
    }

    public class Where implements QueryToken {

      private final Type type;
      private final String column;
      private final String value;

      public static Where equals(String column, String value) {
        return new Where(Type.EQ, column, value);
      }

      public static Where notEquals(String column, String value) {
        return new Where(Type.NE, column, value);
      }

      public static Where lessThan(String column, String value) {
        return new Where(Type.LE, column, value);
      }

      public static Where lessOrEq(String column, String value) {
        return new Where(Type.LE, column, value);
      }

      public static Where greaterThan(String column, String value) {
        return new Where(Type.GT, column, value);
      }

      public static Where greaterOrEq(String column, String value) {
        return new Where(Type.GE, column, value);
      }

      public static Where in(String column, String... values) {
        String list =
            Arrays.stream(values)
                .map(s -> "\'" + s + "\'")
                .collect(Collectors.joining(", "));
        return new Where(Type.IN, column, "(" + list + ")");
      }

      public static Where like(String column, String pattern) {
        return new Where(Type.EQ, column, pattern);
      }

      public static Where between(String column, String min, String max) {
        return new Where(Type.BETWEEN, column, min + " AND " + max);
      }

      private Where(Type type, String column, String value) {
        this.type = type;
        this.column = column;
        this.value = value;
      }

      public String assemble() {
        return "WHERE " + column + " " + type.toString() + " " + value;
      }

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

        @Override
        public String toString() {
          return switch (this) {
            case EQ -> "==";
            case NE -> "<>";
            case LT -> "<";
            case LE -> "<=";
            case GT -> ">";
            case GE -> ">=";
            case IN -> "IN";
            case LIKE -> "LIKE";
            case BETWEEN -> "BETWEEN";
            default -> throw new RuntimeException();
          };
        }

      }

    }

    abstract String assemble();
  }
}
