export function convertToStringParams(request: any): { [param: string]: string } {
  const params: { [param: string]: string } = {}

  Object.entries(request).forEach(([key, value]) => {
    if (value !== undefined) {
      params[key] = String(value)
    }
  })

  return params
}
