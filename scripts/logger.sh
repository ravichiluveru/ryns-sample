#TERMINAL HINTS
T_NORMAL="\033[0m" # terminal to normal
T_BOLD="\033[1m"   # bold
T_ITALIC="\033[3m" # italic

FGRED="\033[31m"     # foreground to red
FGGREEN="\033[32m"   # foreground to green
FGYELLOW="\033[33m"  # background to yellow
FGMAGENTA="\033[35m" # foreground to magenta
FGCYAN="\033[36m"    # foreground to cyan
FGWHITE="\033[37m"   # foreground to white
FGBLUE="\u001b[34m" # foreground to blue

logIt() {
  echo "$1" >&2
}

logInfo() {
  echo -e "${T_BOLD}${FGCYAN}$1${T_NORMAL}" >&2
}
logError() {
  echo -e "${T_BOLD}${FGRED}$1${T_NORMAL}" >&2
}
logWarn() {
  echo -e "${T_BOLD}${FGYELLOW}$1${T_NORMAL}" >&2
}
logTodo() {
  echo -e "${T_BOLD}${FGMAGENTA}$1${T_NORMAL}" >&2
}
logTip() {
  echo -e "${T_BOLD}${FGBLUE}$1${T_NORMAL}" >&2
}