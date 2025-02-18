export class Result<T> {
  success: boolean
  message: string
  code: number
  data: T
  constructor(success: boolean, message: string, code: number, data: T) {
    this.success = success
    this.message = message
    this.code = code
    this.data = data
  }

  static success(data: any) {
    return new Result(true, '请求成功', 200, data)
  }
  static error(message: string, code: number) {
    return new Result(false, message, code, null)
  }
}

export default Result
