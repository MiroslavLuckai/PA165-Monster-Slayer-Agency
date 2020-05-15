import React from 'react'

interface IProps {
    className: string,
    image: string,
    alt: string,
}

const ResourceImage: React.FC<IProps> = (props) => {
    return (
        <img
            className={props.className}
            src={require(`../../../../msa-sample-data/src/main/resources/${props.image}`)}
            alt={props.alt} />
    )
}

export default ResourceImage
