
interface HttpResponseData<T> {
    statusCode: number;
    ok: boolean;
    body: T;
}

export default HttpResponseData;