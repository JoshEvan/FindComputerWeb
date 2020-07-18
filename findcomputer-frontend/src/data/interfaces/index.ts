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

export { ITransaction, ITransactionDetail, /*initTransaction,*/ initTransactionDetail } from './transactions/ITransaction'
export { IIndexTransactionRequest, getInitIndexTransactionRequest } from './transactions/IIndexTransaction'
export { IUpsertTransactionRequest,IUpsertTransactionDetailRequest } from './transactions/IUpsertTransaction'

export { ILoginRequest, IRegisterRequest, convIRegisterRequestToILoginRequest } from './IAuth';
export { ICategory, IIndexCategoryResponse } from './category';