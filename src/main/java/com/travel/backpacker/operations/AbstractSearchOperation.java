package com.travel.backpacker.operations;

import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.models.search.AccomSearch;
import com.travel.backpacker.models.search.GlobalSearch;

public class AbstractSearchOperation extends AbstractOperation {
    protected AbstractSearchOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents) {
        super(userWrapper, requiredComponents);
    }

    protected GlobalSearch createSearch(GlobalSearch globalSearch) {
        if (globalSearch instanceof AccomSearch) {
            AccomSearch accomSearch = (AccomSearch) globalSearch;
            return accomSearch;
        }
        return globalSearch;
    }
}
