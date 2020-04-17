package liza.stage.magic.models.magiccards.dtos;

import java.util.ArrayList;
import java.util.List;

public class PagingResult<T> {
    public List<T> data;
    public long total;

    public PagingResult(List<T> data, long total) {
        this.data = data;
        this.total = total;
    }

    public PagingResult() {
        data = new ArrayList<>();
        total = 0;
    }
}
