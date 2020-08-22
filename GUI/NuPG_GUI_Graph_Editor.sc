NuPG_GUI_Graph_Editor {

	var <>window;
	var <>stack;
	var <>multislider;
	var <>scanningSlider;
	var <>numBox;
	var <>menu;
	var <> dataToPDF;
    var <>tablePath;

	build {|colorScheme = 0, coordinates, data, paramName, dataSlot|

		var view, viewLayout, slotGrid, slots, actions;
		var defs = NuPG_GUI_definitions;
		var files=(tablePath ++"/*.wav").pathMatch;
		var fileNames = files.collect{|i| PathName(i).fileName};
		var dataPlotter = NuPG_Plotter.new;


		window = Window.new(paramName,
			Rect.fromArray(defs.nuPGDimensions[11 + coordinates]), resizable: false);
		window.userCanClose = false;
		window.layout_(stack = StackLayout.new());

		//view for each stack
		view = 3.collect{defs.nuPGView(1)};
		//view layout
		viewLayout = 3.collect{GridLayout.new()};
    	3.collect{|i| view[i].layout_(viewLayout[i].margins_([3,3,3,3]).spacing_(1))};

        //4 slots for 3 stacks
		slots = 3.collect{ 5.collect { defs.nuPGView(colorScheme) } };
		//grid - to organise objects - for each slot
		slotGrid = 3.collect{ 5.collect { GridLayout.new().margins_([3,3,3,3]) } };
		//layout for each view
		//add content to each slot
		3.collect{|i| 5.collect{|l| slots[i][l].layout_(slotGrid[i][l])} };

		multislider = 3.collect{};
		scanningSlider = 3.collect{};
		numBox = 3.collect{2.collect{}};
		//menus
		menu = 3.collect{};
		//save data as PDF
		dataToPDF =  3.collect{};

		3.collect{|i|
			var label = ["_max", "_min"];
			var shiftT = [0, 8];
			var shiftN =[1, 9];

			//scanningSlider
			scanningSlider[i] = defs.nuPGSlider()
				.thumbSize_(1)
				.knobColor_(Color.new255(250, 100, 90))
				.background_(Color.gray(1, 0))
				.visible_(0);

			slotGrid[i][0].addSpanning(scanningSlider[i], 0, 0, columnSpan: 20, rowSpan: 10);

			//customise multislider
			multislider[i] = defs.nuPGMultislider()
			.background_(Color.gray(1, 0))
			.action_({|ms|

				data.data_sequencer[i][dataSlot].value = ms.value;

			});

			slotGrid[i][0].addSpanning(multislider[i], 0, 0, columnSpan: 20, rowSpan: 10);

            2.collect{|l|
				slotGrid[i][0].addSpanning(defs.nuPGText(label[l], 20, 30), shiftT[l], 21);
				numBox[i][l] = defs.nuPGNumberBox(20, 30);
			    slotGrid[i][0].addSpanning(numBox[i][l], shiftN[l], 21);
			};



		};


			/*3.collect{|i|

		2.collect{|l|
			numBox[i][l] = defs.nuPGNumberBox(20, 20);
			slotGrid[i][4].addSpanning(numBox[i][l], 0, shiftN[l]);
			}
		};*/


		3.collect{|i|

			//slot 1 = save, load, menu
			2.collect{|l|
				var states = [["open"],["save"]];
				var actions = [
					//open action
					{Dialog.openPanel({ arg path;
						var size, file, temp, array;
						file = SoundFile.new;
						file.openRead(path);
						temp = FloatArray.newClear(4096);
						file.readData(temp);
						array = temp.asArray.resamp1(2048).copy;
						array = array.linlin(-1.0, 1.0, 0.0, 1.0);
						data.data_sequencer[i][dataSlot].value_(array);
					},{"cancelled".postln}
					)},
					//save action
					//opens by default the TABLES directory of the app
					{Dialog.savePanel({ arg path, recHeaderFormat = "wav";

						},{"cancelled".postln}, path: tablePath
					)}
				];

				slotGrid[i][2].addSpanning(defs.nuPGButton([states[l]], 20, 50)
					.mouseDownAction_(actions[l])
					, 0, 0 + l);

			};

			menu[i] = defs.nuPGMenu(defState: 1);
			slotGrid[i][2].addSpanning(menu[i], 0, 2);
			menu[i].items = [];
			menu[i].items = fileNames;

			menu[i].action_({|item|
				var size, dataFile, file, temp, array;
				dataFile = tablePath ++ fileNames[menu[i].value];
				file = SoundFile.new;
				file.openRead(dataFile);
				temp = FloatArray.newClear(4096);
				file.readData(temp);
				array = temp.asArray.resamp1(2048).copy;
				array = array.linlin(-1.0, 1.0, 0.0, 1.0);

				data.data_sequencer[i][dataSlot].value = array;

				fileNames[menu[i].value].postln;
			});

			dataToPDF[i] = defs.nuPGButton([["toPDF"]], 20, 50)
			.action_({dataPlotter.saveData(data.data_sequencer[i][dataSlot].value, 400, 100)});

			slotGrid[i][2].addSpanning(dataToPDF[i], 0, 3);

		};


		3.collect{|i|
			//SLOT 2 - norm, reverse, invert

			4.collect{|l|
				var states = ["norm", "R", "I", "F"];
			    var action = [
					//normalize to 0, 1
					{
						var array;

						array = data.data_sequencer[i][dataSlot].value.deepCopy;
						array = array.normalize;
						data.data_sequencer[i][dataSlot].value = [0.0,1.0].asSpec.map(array);
					},
					//reverse
					{
						var array;

						array = data.data_sequencer[i][dataSlot].value.deepCopy;
						array = array.reverse;
						data.data_sequencer[i][dataSlot].value = array;
					},
					//invert
					{
						var array;

						array = data.data_sequencer[i][dataSlot].value.deepCopy;
						array = array.invert;
						data.data_sequencer[i][dataSlot].value = array;
					},
					//flop
					{
						var array;

						array = data.data_sequencer[i][dataSlot].value.deepCopy;
						array = array.linlin(0.0, 1.0, 0.0, -1.0);
						data.data_sequencer[i][dataSlot].value = array.linlin(-1.0, 0.0, 0.0,1.0);
					}
				];


				slotGrid[i][3].addSpanning(defs.nuPGButton([[states[l]]], 20, 50)
					.mouseDownAction_(action[l]), 0, l);
			};

		};



        3.collect{|i|
			viewLayout[i].addSpanning(slots[i][0], 0, 0, columnSpan: 10);
			//viewLayout[i].addSpanning(slots[i][4], 0, 11);
			viewLayout[i].addSpanning(slots[i][2], 1, 0);
			viewLayout[i].addSpanning(slots[i][3], 1, 1);
		};



		3.collect{|i| stack.add(view[i])};


		//^window.front

	}
	visible {|boolean|
		^window.visible = boolean
	}


}