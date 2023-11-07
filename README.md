# Cmarkup stands for Container Markup
Minecraft server plugin to markup containers with ease.

## Theory
Cmarkup is a data class that holds data required for inventory creation, but alongside it stores slot keys.

Slot keys is a map of strings and slot indicies set.

Each slot in container may have one or many keys.

## Practice

### Container-based menus

Suppose the development of 9 slots paged menu where the last slot is the load next button, the pre-last slot is the reset to the first page button, and the other first slots are page items.

The JSON representation of this menu:

```json
{
  "title": "Paged Menu",
  "titleColor": "#ffffff",
  "size": 9,
  "type": null,
  "slots": {
    "page_item": [0, 1, 2, 3, 4, 5, 6],
    "page_reset": [7],
    "page_next": [8]
  }
}
```

Here is first 7 slots are marked with `page_item` key, pre-last with `page_reset`, and last with `page_next`.

In code, to handle user interactions with slots, these slot keys could be referenced to implement specific behaviour.

# Usage

## Plugin

The plugin provides commands to manipulate markups in-game, so the slot keys and container could be setted up rapidly.

The markup files are stored in `markups` folder of the plugin directory: `plugins/Cmarkup/markups/*.json`.

## Library

`ContainerMarkup` is a data class that is serializable, so the markups can be loaded and saved in any format.

`MarkupBaseInventory` is an inheritable class that constructs inventory using `ContainerMarkup`.
