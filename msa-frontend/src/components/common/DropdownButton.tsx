import React from 'react'
import 'styles/DropdownButton.scss'
import 'styles/ui.scss'
import Icon from 'components/common/Icon'
import {EIcon, EIconStyle} from 'enums/EIcon'
import {getDisplayText} from 'utils/common'
import {IDropdownItem} from 'types/IDropdownItem'

interface IOwnProps {
    displayText: string,
    dropdownList: IDropdownItem[],
    isSelected: boolean,
}

interface IProps extends IOwnProps {}

const DropdownButton: React.FC<IProps> = (props) => {
    const [isOpen, setIsOpen] = React.useState(false)
    const [selectedValue, setSelectedValue] = React.useState<string | null>(null)

    const buttonClassName = `ui-dropdown-button ${!props.isSelected ? 'ui-dropdown-button--reverted' : ''}`
    const dropdownClassName = `dropdown ${isOpen ? 'dropdown--open' : ''}`

    return (
        <div className={'scope__DropdownButton'}>
            <button className={buttonClassName} onClick={() => setIsOpen(!isOpen)}>
                <span className={'display-text'}>
                    {props.isSelected ? getDisplayText(selectedValue!) : getDisplayText(props.displayText)}
                </span>
                <span className={'arrow'}><Icon icon={EIcon.CARET_DOWN} style={EIconStyle.SOLID} /></span>
            </button>
            <ul className={dropdownClassName}>
                {props.dropdownList.map((item, index) => {
                    const {value, onClick} = item
                    const onItemClick = (value: any) => {
                        setSelectedValue(value)
                        onClick(value)
                        setIsOpen(false)
                    }

                    return (
                        <li
                            className={'dropdown__item'}
                            key={index}
                            onClick={() => onItemClick(value)}
                        >
                            {getDisplayText(value)}
                        </li>
                    )
                })}
            </ul>
        </div>
    )
}

export default DropdownButton
