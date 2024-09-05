package stugbokningssytem_bff.common;

public class PaginationUtils {

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_SIZE = 10;

  private PaginationUtils() {
    // Private constructor to prevent instantiation
  }

  public static int getPage(int page) {
    return page >= 0 ? page : DEFAULT_PAGE;
  }

  public static int getSize(int size) {
    return size > 0 ? size : DEFAULT_SIZE;
  }
}
