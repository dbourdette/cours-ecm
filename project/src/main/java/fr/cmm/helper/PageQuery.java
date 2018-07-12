package fr.cmm.helper;

public class PageQuery {
    // 0 based page index
    private int index = 0;

    private int size = 20;

    private String tag;

    public int skip() {
        return index * size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageQuery pageQuery = (PageQuery) o;

        if (index != pageQuery.index) return false;
        if (size != pageQuery.size) return false;
        return !(tag != null ? !tag.equals(pageQuery.tag) : pageQuery.tag != null);

    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + size;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
