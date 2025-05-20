package may.com.mm.component.persistence;

import java.util.List;

public record PagedResult<T>(int page, int pageSize, int totalRecords, int totalPages, List<T> data) { }
