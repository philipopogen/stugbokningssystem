package stugbokningssytem_bff.constants;

public enum MessageText {
  BOKNIG_BEKRAFTELSE("Tack {} för din bokning. Din bokningsId är {} "),
  BOKNING_REDAN_FINNS_FEL("Det finns redan en bokning för valt datum"),
  VISA_BOKNING_OBEHORIG_FEL("Du har inte behörighet att se bokning"),
  INCHECKNING_DATUM_FORE_NUTID_FEL("Valt inckeckningsdatum kan inte före nutid");
  private String text;

  MessageText(String text) {
    this.text = text;
  }

  public String getText() {
    return String.format(this.text);
  }

  public String getText(Object... params) {
    String result = this.text;
    if (params != null && params.length > 0) {
      for (Object param : params) {
        result = result.replaceFirst("\\{\\}", "" + param);
      }
    }
    return String.format(result);
  }
}
