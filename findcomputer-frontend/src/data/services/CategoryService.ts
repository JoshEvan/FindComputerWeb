import { getBaseUrl, FINDCOMP_URL } from "../../configs/api";
import  Axios from  'axios-observable';
import { Observable } from "rxjs";
import { IIndexCategoryResponse } from "../interfaces";

const usingBaseUrl = getBaseUrl()

export const serviceIndexCategory = (): Observable<IIndexCategoryResponse> => {
    return Axios.get(
        usingBaseUrl+FINDCOMP_URL.CAT.INDEX
    )
}