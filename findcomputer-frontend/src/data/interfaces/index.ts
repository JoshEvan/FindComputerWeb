export enum HTTPCallStatus{
    Success = 'SUCCESS',
    Failed = 'FAILED'
}
export interface BaseResponse{
    status:HTTPCallStatus,
    data:{}
}

export { ICRUDResponse } from './ICRUD'

export { IItem } from './items/IItem';
export { IIndexItemRequest, IIndexItemResponse } from './items/IIndexItem';
export { IDeleteItemResponse } from './items/IDeleteItem';
export { IUpdateItemRequest, IUpsertItemResponse, IInsertItemRequest } from './items/IUpsertItem';
export { ILoginRequest, IRegisterRequest, convIRegisterRequestToILoginRequest, IUpdateUserRequest } from './IAuth';
export { ICategory, IIndexCategoryResponse } from './category';