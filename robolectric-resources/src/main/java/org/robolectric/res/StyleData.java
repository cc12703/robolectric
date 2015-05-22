package org.robolectric.res;

import org.robolectric.util.Strings;

import java.util.LinkedHashMap;
import java.util.Map;

public class StyleData implements Style {
  private final String packageName;
  private final String name;
  private final String parent;
  private final Map<ResName, Attribute> items = new LinkedHashMap<>();

  public StyleData(String packageName, String name, String parent) {
    this.packageName = packageName;
    this.name = name;
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public String getParent() {
    return parent;
  }

  public void add(ResName attrName, Attribute attribute) {
    attrName.mustBe("attr");
    items.put(attrName, attribute);
  }

  @Override public Attribute getAttrValue(ResName resName) {
    resName.mustBe("attr");
    Attribute attribute = items.get(resName);

    // yuck. hack to work around library package remapping
    if (attribute == null && !"android".equals(resName.packageName)) {
      attribute = items.get(resName.withPackageName(packageName));
      if (attribute != null && (!"android".equals(attribute.contextPackageName))) {
        attribute = new Attribute(resName, attribute.value, resName.packageName);
      }
    }
    return attribute;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof StyleData)) {
      return false;
    }
    StyleData other = (StyleData) obj;

    return Strings.equals(packageName, other.packageName)
        && Strings.equals(name, other.name)
        && Strings.equals(parent, other.parent)
        && items.size() == other.items.size();
  }

  @Override
  public int hashCode() {
    int hashCode = 0;
    hashCode = 31 * hashCode + Strings.nullToEmpty(packageName).hashCode();
    hashCode = 31 * hashCode + Strings.nullToEmpty(name).hashCode();
    hashCode = 31 * hashCode + Strings.nullToEmpty(parent).hashCode();
    hashCode = 31 * hashCode + items.size();
    return hashCode;
  }

  @Override public String toString() {
    return "StyleData{" +
        "name='" + name + '\'' +
        ", parent='" + parent + '\'' +
        '}';
  }

  public String getPackageName() {
    return packageName;
  }
}
