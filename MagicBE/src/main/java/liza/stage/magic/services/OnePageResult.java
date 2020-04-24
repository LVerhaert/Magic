package liza.stage.magic.services;

import java.util.List;

public class OnePageResult<T> {
    public List<T> data;
    public long total;

    public OnePageResult(List<T> data, long total) {
        this.data = data;
        this.total = total;
    }
}
