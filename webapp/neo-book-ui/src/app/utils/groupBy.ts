export const groupBy = <T extends { [key: string]: any }>(property: keyof T, objects: T[]) => {
  return objects.reduce((result, object) => {
    const currentObjectsForProperty = result[object[property]] || [];
    result[object[property]] = [...currentObjectsForProperty, object];
    return result;
  }, {} as { [key: string]: T[] });
}