//package com.travel.backpacker.service.operations;
//
//import com.travel.backpacker.dto.UserWrapper;
//import com.travel.backpacker.dto.search.AccomSearch;
//import com.travel.backpacker.dto.search.GlobalSearch;
//
//public class AbstractSearchOperation extends AbstractOperation {
//    protected AbstractSearchOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents) {
//        super(userWrapper, requiredComponents);
//    }
//
//    protected GlobalSearch createSearch(GlobalSearch globalSearch) {
//        if (globalSearch instanceof AccomSearch) {
//            AccomSearch accomSearch = (AccomSearch) globalSearch;
//            return accomSearch;
//        }
//        return globalSearch;
//    }
//}
