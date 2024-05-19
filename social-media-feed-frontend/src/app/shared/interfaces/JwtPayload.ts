
interface JwtPayload {
    accountId: string;
    sub: string;
    iat: number;
    exp: number;
}

export default JwtPayload;