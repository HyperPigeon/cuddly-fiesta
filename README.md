# Wheel of Wacky

A mod that adds a carnival prize wheel that - when spun - has the chance to either fabulously reward or horrifically punish the player that spun the wheel in a hilarious fashion! 

## Creating spells

To start creating your own custom spells, you'll first have to create a data pack folder. If you don't know how to make a data pack, here is a link to a tutorial to help you get started: [Creating a data pack](https://minecraft.wiki/w/Tutorials/Creating_a_data_pack).

If you haven't already, you'll want to create a `data` folder in your data pack folder. Then you'll want to a create a folder named `wacky_wheel` within your `data` folder. After which you will have to create a folder called `spell_type` in the `wacky_wheel` folder. 

Your file path should look like this: `<your data pack folder>/data/wacky_wheel/spell_type`. 

Now you'll want to create a JSON file in  `<your data pack folder>/data/wacky_wheel/spell_type`. For the purpose of this example, we'll call it `free_diamond.json`. 

The first thing we'll want to do is give a spell a name that will be displayed to the player when they roll that spell on the wheel.

```
{
  "name": "Free Diamond!",
}
```

We can also optionally set a specific color for the name text and add some flavor text to be displayed underneath it:

```
{
  "name": "Free Diamond!",
  "titleColor": "#ADD8E6",
  "flavorText": "Enjoy your free diamond :)",
}
```

Next, we have to provide our spell with an item id that corresponds to an in-game item. This item will be used to represent the spell on wheel slices. 

```
{
  "name": "Free Diamond!",
  "titleColor": "#ADD8E6",
  "flavorText": "Enjoy your free diamond :)",
  "itemID": "minecraft:diamond",
}
```

The next field we have to define is `castingTime`, which represents the amount of time (in ticks) that it will take to cast the spell after the wheel lands on it:

```
{
  "name": "Free Diamond!",
  "titleColor": "#ADD8E6",
  "flavorText": "Enjoy your free diamond :)",
  "itemID": "minecraft:diamond",
  "castingTime": 60,
}
```

The last step is the provide the name of .mcfunction file that will be used to provide the actual spell effect. 

```
{
  "name": "Free Diamond!",
  "titleColor": "#ADD8E6",
  "flavorText": "Enjoy your free diamond :)",
  "itemID": "minecraft:diamond",
  "castingTime": 60,
  "onCastFunction": "give_diamond"
}
```

Of course, we'll actually have a create a .mcfunction file named `give_diamond.mcfunction` in `<your data pack folder>/data/wacky_wheel/functions`. 

```
give @s diamond
```

Keep in mind that the `onCastFunction` will only and always the target the player who initially spun the wheel. 

To test our new command, enter the /wheel free_diamond command into chat and receive your free diamond. 

### Spell JSON Keys

`name`: The name of the spell.
(Optional) `titleColor`: Color of the name text/title.
(Optional) `flavorText`: Additional information that appears underneath the spell's name. 
(Optional) `flavorTextColor`: Color of the flavor text subtitle. 
`itemID`: ID of the item used to represent the spell on the wacky wheel. 
`castingTime`: Time in ticks that it takes to cast the spell.
`onCastFunction`: Name of the .mcfunction file that executes to create the spell effect.
(Optional) `duration`: Time in ticks that the spell lasts.
(Optional) `onEndFunction`: Name of the .mcfunction file that executes when the spell ends. 


