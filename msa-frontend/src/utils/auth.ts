import {ICredentials} from 'types/ICredentials'
const validate = require('validate.js')

export const areCredentialsValid = (credentials: ICredentials): boolean => {
    const {email, password} = credentials
    if (!email || !password) {
        return false
    }

    const constraints = {
        from: {
            email: true,
        }
    }

    return !validate({from: email}, constraints)
}
