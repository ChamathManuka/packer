package com.travel.backpacker.operations;

import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.models.search.GlobalSearch;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SearchOperation extends AbstractSearchOperation implements Operation<UnknownUser> {
    private final String searchType;

    public SearchOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents, String type) {
        super(userWrapper, requiredComponents);
        this.searchType = type;
    }

    @Override
    public HttpEntity execute(UnknownUser unknownUser) {
        GlobalSearch globalSearch = requiredComponents.getGlobalSearch();
        if (searchType.equalsIgnoreCase("accom")) {

            return globalSearch.execute();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
