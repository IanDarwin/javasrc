library(shiny)

# Define UI for app that draws a histogram ----
min_year <- floor(time(sunspots)[1]);
max_year <- floor(time(sunspots)[length(time(sunspots))])

ui <- fluidPage(

  # App title ----
  titlePanel("Interactive R Statistics Demo"),

  # Sidebar layout with input and output definitions ----
  sidebarLayout(

	# Sidebar panel for inputs ----
	sidebarPanel(

		# Input: Slider for the starting year
		sliderInput(inputId = "startYear",
				  label = "Starting Year:",
				  min = min_year,
				  max = max_year,
				  value = min_year),

		# Input: Slider for the ending year
		sliderInput(inputId = "endYear",
				  label = "Starting Year:",
				  min = min_year,
				  max = max_year,
				  value = max_year)

	),

	# Main panel for displaying outputs ----
	mainPanel(

	  # Output one plot -----
	  plotOutput(outputId = "distPlot")
	)
  )
)

# Define server logic required to draw the plot:
server <- function(input, output) {

  # plot of sunspot data with user-selectable range
  # Plot code wrapped in renderPlot to indicate that:
  # it's reactive and generates a plot.

  output$distPlot <- renderPlot({

	x	<- window(sunspots,start=input$startYear,end=input$endYear)

	plot(x, col = "#75AADB",
		xlab = 'Year', ylab = "Sunspot Activity",
		main = sprintf("Year vs Sunspot activity %d to %d", input$startYear, input$endYear)
		)
	})
}

shinyApp(ui = ui, server = server)
