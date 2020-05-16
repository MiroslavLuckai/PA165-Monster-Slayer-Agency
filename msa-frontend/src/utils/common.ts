export const getDisplayText = (string: string) => {
    const lowercaseString = string.toLowerCase()
    return (lowercaseString.charAt(0).toUpperCase() + lowercaseString.slice(1)).replace('_', ' ')
}
