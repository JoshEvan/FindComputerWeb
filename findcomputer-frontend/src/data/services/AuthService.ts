import { Observable, BehaviorSubject, ObservableLike } from "rxjs";
import Axios from "axios-observable";
import { getLoginUrl, getBaseUrl, FINDCOMP_URL } from "../../configs/api";
import { ILoginRequest, IRegisterRequest, IUpdateUserRequest } from "../interfaces";

export const serviceLogin = (dataPayload: ILoginRequest): Observable<any> => {
    return Axios.post(
        getLoginUrl(),
        dataPayload
    )
}

export const serviceRegister = (dataPayload: IRegisterRequest): Observable<any> => {
    return Axios.post(
        getBaseUrl()+FINDCOMP_URL.USER.REGISTER,
        dataPayload
    )
}

export const serviceShowUser = (username: string) : Observable<any> => {
    return Axios.get(
        getBaseUrl()+FINDCOMP_URL.USER.SHOW+username
    )
}

export const serviceUpdateUser = (dataPayload:IUpdateUserRequest) :Observable<any> => {
    return Axios.put(
        getBaseUrl()+FINDCOMP_URL.USER.UPDATE,
        dataPayload
    )
}