<chart>
symbol=EURUSD
period=240
leftpos=408
digits=5
scale=8
graph=1
fore=1
grid=1
volume=0
scroll=0
shift=1
ohlc=1
one_click=0
askline=0
days=1
descriptions=0
shift_size=20
fixed_pos=0
window_left=307
window_top=159
window_right=614
window_bottom=318
window_type=1
background_color=0
foreground_color=16777215
barup_color=65280
bardown_color=65280
bullcandle_color=0
bearcandle_color=16777215
chartline_color=65280
volumes_color=3329330
grid_color=10061943
askline_color=255
stops_color=255

<window>
height=148
<indicator>
name=main
</indicator>
</window>

<window>
height=37
<indicator>
name=MACD
fast_ema=12
slow_ema=26
macd_sma=9
apply=0
color=3329330
style=0
weight=1
signal_color=255
signal_style=0
signal_weight=1
levels_color=12632256
levels_style=2
levels_weight=1
level_0=0.0000
period_flags=0
show_data=1
</indicator>
</window>

<window>
height=35
<indicator>
name=Stochastic Oscillator
kperiod=8
dperiod=3
slowing=3
method=0
apply=0
color=3329330
style=0
weight=1
color2=255
style2=0
weight2=1
min=0.000000
max=100.000000
levels_color=12632256
levels_style=2
levels_weight=1
level_0=20.0000
level_1=80.0000
period_flags=0
show_data=1
</indicator>
</window>

<window>
height=50
<indicator>
name=Relative Strength Index
period=13
apply=0
color=3329330
style=0
weight=1
min=0.000000
max=100.000000
levels_color=12632256
levels_style=2
levels_weight=1
level_0=30.0000
level_1=70.0000
period_flags=0
show_data=1
</indicator>
<indicator>
name=Relative Strength Index
period=3
apply=0
color=255
style=0
weight=1
min=0.000000
max=100.000000
levels_color=12632256
levels_style=2
levels_weight=1
level_0=30.0000
level_1=70.0000
period_flags=0
show_data=1
</indicator>
</window>

<expert>
name=MACD Sample
flags=471
window_num=-1
<inputs>
TakeProfit=50.00000000
Lots=0.10000000
TrailingStop=30.00000000
MACDOpenLevel=3.00000000
MACDCloseLevel=2.00000000
MATrendPeriod=26.00000000
</inputs>
</expert>
</chart>

